package com.tianxing.entity.http;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by tianxing on 16/9/2.
 */
public class HttpAudio extends RequestBody {
    /**
     * Returns the Content-Type header for this body.
     */
    @Override
    public MediaType contentType() {
        return null;
    }

    /**
     * Writes the content of this request to {@code out}.
     *
     * @param sink
     */
    @Override
    public void writeTo(BufferedSink sink) throws IOException {

    }
}
