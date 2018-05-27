package comben.mkleids.com.mkleids;

import java.util.ArrayList;


public class ItemInfo implements Comparable{

	public final static int PENDING = 0;
	public final static int SUCCESS = 1;
	public final static int FAILED = 2;

	public String time;
	public ArrayList<String> appName = new ArrayList<String>();
	public long size;
	public String appInfo;
	public int id = -1;
	public int type;
	public String sType = "";
	public boolean enbled = false;
	public boolean checked = false;
	public String path = null;
	public int result = PENDING;
	
	public ItemInfo(String time0){
		time = time0;
	}

	@Override
	public int compareTo(Object arg0) {
		
		ItemInfo other = (ItemInfo)arg0;
		
		if(time != null){
			return (int) ( size - other.size );
		}
		return 0;
	}
	
	
}
