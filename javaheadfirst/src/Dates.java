import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class Dates {
	public static void main(String[] args) {
		String dateStr = "Thursday, 25 January, 2018, 9:53 PM";
		try {
			Date date = new SimpleDateFormat("EEEE, dd MMMM, yyyy, h:mm a").parse(dateStr);
			System.out.println(new SimpleDateFormat("dd-MMM-yy hh:mma").format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
