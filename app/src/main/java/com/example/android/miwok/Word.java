package com.example.android.miwok;

public class Word {

    private String mMiwokWord;
    private String mTranslatedWord;
    private final int NO_IMAGE = -1;
    private int mImageIdResource = NO_IMAGE;

    public Word(String miwokWord, String translationWord){
        mMiwokWord = miwokWord;
        mTranslatedWord = translationWord;
    }
    /*
    * Create a new Word Object
    * @param miwokWord  Is the word in the miwok language
    * @param translationWord  Is the word in the language we want to make the translation
    * @param imageResourceId  Is the resource of the image that represents the word
    *
    * */
    public Word(String miwokWord, String translationWord, int imageResourceId){
        mMiwokWord = miwokWord;
        mTranslatedWord = translationWord;
        mImageIdResource = imageResourceId;
    }
    public String getMiwokWord() {
        return mMiwokWord;
    }

    public String getTranslatedWord() {
        return mTranslatedWord;
    }

    public int getImageIdResource(){ return mImageIdResource;}

    public boolean hasImage(){ return mImageIdResource != NO_IMAGE;}
}
