package com.example.android.advance2_lesson2_mvp.screen.data.source.remote.api.service;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/**
 * Created by VinhTL on 18/10/2017.
 */

public class BooleanAdapter extends TypeAdapter<Boolean>{

    /**
     * Writes one JSON value (an array, object, string, number, boolean or null)
     * for {@code value}.
     *
     * @param value the Java object to write. May be null.
     */
    @Override
    public void write(JsonWriter out, Boolean value) throws IOException {
        if(value == null){
            out.nullValue();
            return;
        }
        out.value(value);
    }

    /**
     * Reads one JSON value (an array, object, string, number, boolean or null)
     * and converts it to a Java object. Returns the converted object.
     *
     * @return the converted Java object. May be null.
     */
    @Override
    public Boolean read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek){
            case NULL:
                in.nextNull();
                return null;
            case BOOLEAN:
                return in.nextBoolean();
            case NUMBER:
                return in.nextInt() != 0;
            case STRING:
                return Boolean.valueOf(in.nextString());
            default:
                return null;
        }
    }
}
