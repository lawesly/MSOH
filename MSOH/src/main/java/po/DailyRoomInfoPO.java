package po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 
 * @author zqh
 *
 */
public class DailyRoomInfoPO implements Serializable {
	private static final long serialVersionUID=1L;
	// 酒店ID
	private String hotelID;
	// 日期
	private Timestamp date;
	// 当日可用房间类型及数量
	private List<RoomPO> room;

	public DailyRoomInfoPO() {
	}

	public DailyRoomInfoPO(String hotelID, Timestamp date, List<RoomPO> room) {
		this.hotelID = hotelID;
		this.date = date;
		this.room = room;
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public List<RoomPO> getRoom() {
		return room;
	}

	public void setRoom(List<RoomPO> room) {
		this.room = room;
	}

}
