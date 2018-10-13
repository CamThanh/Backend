package com.vn.camthanh.book.services;

import java.io.IOException;
import java.util.zip.ZipInputStream;

public interface BookService {

	Object readZipEntry(ZipInputStream zi) throws IOException;
}
