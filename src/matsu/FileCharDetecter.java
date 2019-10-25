package matsu;

import org.mozilla.universalchardet.UniversalDetector;

	public class FileCharDetecter {
	/**
	 * 文字コードを判定するクラス.
	 * @author Administrator
	 */

	  private String file;

	  // コンストラクタ
	  public FileCharDetecter(String file) {
	    this.file = file;
	  }
	  /**
	   * 文字コードを判定するメソッド.
	   * @param ファイルパス
	   * @return 文字コード
	   */
	  public String detector() throws java.io.IOException {
	    byte[] buf = new byte[4096];
	    String fileName = this.file;
	    @SuppressWarnings("resource")
		java.io.FileInputStream fis = new java.io.FileInputStream(fileName);

	    // 文字コード判定ライブラリの実装
	    UniversalDetector detector = new UniversalDetector(null);

	    // 判定開始
	    int nread;
	    while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
	      detector.handleData(buf, 0, nread);
	    }
	    // 判定終了
	    detector.dataEnd();

	    // 文字コード判定
	    String encType = detector.getDetectedCharset();
	    /*
	    if (encType != null) {
	      System.out.println("文字コード = " + encType);
	    } else {
	      System.out.println("文字コードを判定できませんでした");
	    }
	    */

	    // 判定の初期化
	    detector.reset();

	    return encType;
	  }
	}