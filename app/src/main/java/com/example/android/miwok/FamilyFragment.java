package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyFragment extends Fragment {

    MediaPlayer mediaPlayer;
    AudioManager am;
    AudioManager.OnAudioFocusChangeListener afChangeListener;

    public FamilyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //Instantiate audio manager and audio manager listener
        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                    // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                    // our app is allowed to continue playing sound but at a lower volume. We'll treat
                    // both cases the same way because our app is playing short sound files.

                    // Pause playback and reset player to the start of the file. That way, we can
                    // play the word from the beginning when we resume playback.
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                    mediaPlayer.start();
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                    // Stop playback and clean up resources
                    releaseMediaPlayer();
                }
            }
        };

        //Create an ArrayList of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("әpә","father", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("әṭa","mother", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("angsi","son", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("tune","daughter", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("taachi","older brother", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("chalitti","younger brother", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("teṭe","older sister", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("kolliti","younger sister", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("ama","grandmother", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("paapa","grandfather", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wordSelected = words.get(position);
                releaseMediaPlayer();

                //Request audio focus
                int result = am.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(getActivity(), wordSelected.getSoundResourceId()); //Set the media player to reset it in every single touch
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            am.abandonAudioFocus(afChangeListener);
        }
    }
}