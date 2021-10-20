package android.example.miwokapp;

public class Word {

//    class variables for miwok numbers as well as default words are defined
    String miwok_number_words;
    String default_number_words;

//    we define the constructor for the Custom class
    public Word (String m_word,String d_word){
        miwok_number_words = m_word;
        default_number_words = d_word;
    }

//    Below is the public function to return the number words in Miwok language
    public String getMiwokNumbers() {
        return miwok_number_words;
    }

//    Below is the public function to return the number words in the default language
    public String getDefaultNumbers(){
        return default_number_words;
    }
}
