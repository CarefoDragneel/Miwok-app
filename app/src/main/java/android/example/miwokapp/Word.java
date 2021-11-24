package android.example.miwokapp;

// this custom class is to modify the array list so that it can store more data then a String
public class Word {

    //    class variables for miwok numbers as well as default words are defined
    String miwok_number_words;
    String default_number_words;
    int miwok_image_resource_id = -1;
    int miwok_list_items_audio;

    //    we define the constructor for the Custom class
    public Word(String m_word, String d_word, int audio) {
        miwok_number_words = m_word;
        default_number_words = d_word;
        miwok_list_items_audio = audio;
    }

    //    we overload the constructor to accommodate the image also
    public Word(String m_word, String d_word, int resource_id, int audio) {
        miwok_number_words = m_word;
        default_number_words = d_word;
        miwok_image_resource_id = resource_id;
        miwok_list_items_audio = audio;
    }

    //    Below is the public function to return the number words in Miwok language
    public String getMiwokNumbers() {
        return miwok_number_words;
    }

    //    Below is the public function to return the number words in the default language
    public String getDefaultNumbers() {
        return default_number_words;
    }

    //    Below is the public function to return the resource id of the image
    public int getImageResourceId() {
        return miwok_image_resource_id;
    }

    //    checking if the image is present or not
    public boolean hasImageResource() {
        return (miwok_image_resource_id != -1);
    }

//   method for getting the resource id of the audio file
    public int getAudioResource() {
        return miwok_list_items_audio;
    }
}
