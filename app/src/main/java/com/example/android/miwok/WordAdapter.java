package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mBackgroundColor;
    public WordAdapter(Context context, ArrayList<Word> wordsList, int backgroundColor){
        super(context, 0, wordsList);
        mBackgroundColor = backgroundColor;
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
        if(currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageIdResource());
        }else {
            imageView.setVisibility(View.GONE);
        }
        TextView miwokText = (TextView) listItemView.findViewById(R.id.txtMiwokhWord);
        TextView englishText = (TextView) listItemView.findViewById(R.id.txtEnglishWord);

        miwokText.setText(currentWord.getMiwokWord());
        englishText.setText(currentWord.getTranslatedWord());

        View texBox = listItemView.findViewById(R.id.textBox);
        //We need to convert the resource id of the color into a color
        int color = ContextCompat.getColor(getContext(), mBackgroundColor);
        texBox.setBackgroundColor(color);

        return listItemView;
    }


}
