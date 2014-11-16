/**
 * 
 */
package restConsumingDemo.log;

/**
 * @author asier
 *
 */
public class RcdLogging {
	
	static RcdLogging rcdLogging = null;
	
	public static RcdLogging getInstance() {
		if (rcdLogging != null) {
			rcdLogging = new RcdLogging();
		}
		return rcdLogging;
	}
	
	public void log(String msg) {
		System.out.println(msg);
	}

	public void log(Exception e) {
		e.printStackTrace();
	}
}
