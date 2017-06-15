package unit5;

public class RealData implements Data {

  protected final String result;


  public RealData(String param) {
    super();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < 10; i++) {
      sb.append(param);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    this.result = sb.toString();
  }

  @Override
  public String getResult() {
    return result;
  }

}
