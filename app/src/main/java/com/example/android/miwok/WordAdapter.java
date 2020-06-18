package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Context context, ArrayList<Word> wordsList){
        super(context, 0, wordsList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_itemt, parent, false);
        }

        Word currentWord = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentWord.getImageIdResource());

        TextView miwokText = (TextView) listItemView.findViewById(R.id.txtMiwokhWord);
        TextView englishText = (TextView) listItemView.findViewById(R.id.txtEnglishWord);

        miwokText.setText(currentWord.getMiwokWord());
        englishText.setText(currentWord.getTranslatedWord());

        return listItemView;
    }


}
