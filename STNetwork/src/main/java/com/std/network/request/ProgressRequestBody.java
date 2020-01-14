package com.std.network.request;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020-01-14.
 */
public class ProgressRequestBody extends RequestBody {
    public interface OnProgressListener {
        void onProgress(long byteWritted, long contentLength);
    }

    RequestBody delegate;
    OnProgressListener listener;

    public ProgressRequestBody(RequestBody requestBody, OnProgressListener listener) {
        this.delegate = requestBody;
        this.listener = listener;
    }

    @Override
    public MediaType contentType() {
        return delegate.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        ProgressSink countingSink = new ProgressSink(sink);
        //将CountingSink转化为BufferedSink供writeTo()使用
        BufferedSink bufferedSink = Okio.buffer(countingSink);
        delegate.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    @Override
    public long contentLength() {
        try {
            return delegate.contentLength();
        } catch (IOException e) {
            return -1;
        }
    }

    public class ProgressSink extends ForwardingSink {
        private long byteWritten;

        public ProgressSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            byteWritten += byteCount;
            listener.onProgress(byteWritten, contentLength());
        }
    }
}
