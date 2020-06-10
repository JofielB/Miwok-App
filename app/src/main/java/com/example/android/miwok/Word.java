package com.example.android.miwok;

public class Word {

    private String mMiwokWord;
    private String mTranslatedWord;

    public Word(String miwokWord, String translationWord){
        mMiwokWord = miwokWord;
        mTranslatedWord = translationWord;
    }
    public String getMiwokWord() {
        return mMiwokWord;
    }

    public String getTranslatedWord() {
        return mTranslatedWord;
    }

}
