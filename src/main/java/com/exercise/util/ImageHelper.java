package com.exercise.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageHelper {

  public static boolean verifyImage(byte[] bytes) {
    try {
      if (bytes.length > Constants.IMAGE_MAX_SIZE * 1024 * 1024) {
        return false;
      }
      InputStream in = new ByteArrayInputStream(bytes);
      ImageIO.read(in);
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}
