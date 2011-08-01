package org.ebookdroid.cbdroid.codec;

import org.ebookdroid.core.codec.CodecContext;
import org.ebookdroid.core.codec.CodecDocument;

import android.content.ContentResolver;

public class CbzContext implements CodecContext {

	@Override
	public long getContextHandle() {
		return 0;
	}

	@Override
	public CodecDocument openDocument(String fileName, String password) {
		return CbzDocument.openDocument(fileName);
	}

	@Override
	public void recycle() {

	}

	@Override
	public void setContentResolver(ContentResolver contentResolver) {

	}

}