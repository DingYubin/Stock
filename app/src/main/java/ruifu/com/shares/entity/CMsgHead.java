package ruifu.com.shares.entity;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public class CMsgHead implements Serializable{
	/**
	 * 	//  uint8_t flag;         //标识位,值为0xEF
	 //  uint8_t encrypted;    //加密类型,无加密为0
	 //  uint16_t version;     //版本号,当前版本为1
	 //  uint32_t length;      //数据包长度,包含头部信息及请求报文长度
	 //  uint32_t serial;      //序列号,活动连接中唯一标识请求及返回
	 //  uint32_t event;       //事件ID,用于描述报文事件信息
	 //  uint32_t uid;         //用户ID,非用户授权相关操作时为0
	 //  uint32_t reserved;    //保留字段
	 */
	//标识位,值为0xEF
	private byte flag = (byte) 0xEF;
	//加密类型,无加密为0
	private byte encrypted = 0;
	//版本号,当前版本为1
	private short version = 1;
	//数据包长度,包含头部信息及请求报文长度
	private int length;
	//序列号,活动连接中唯一标识请求及返回
	private int serial;
	//事件ID,用于描述报文事件信息
	private int event;
	//用户ID,非用户授权相关操作时为0
	private int userId;
	//保留字段
	private int reserved;

	public CMsgHead(){};

	public CMsgHead(int length, int serial,
					int event, int userId, int reserved) {

		super();
		this.length = length;
		this.serial = serial;
		this.event = event;
		this.userId = userId;
		this.reserved = reserved;
	}



	@Override
	public String toString() {
		return "CMsgHead [flag=" + (int)(flag & 0xFF) + ",encrypted=" + (int)encrypted + ", version=" + version
				+ ", length=" + length + ", serial=" + serial + ", event="
				+ event + ", userId=" + userId + ", reserved=" + reserved + "]";
	}

	public byte getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(byte encrypted) {
		this.encrypted = encrypted;
	}

	public short getVersion() {
		return version;
	}

	public void setVersion(short version) {
		this.version = version;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}


	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public byte[] toBytes(){
		ByteArrayOutputStream bOS = new ByteArrayOutputStream();
		bOS.write(flag);
		bOS.write(encrypted);
		bOS.write(version >> 8);
		bOS.write(version);
		bOS.write(length >> 24);
		bOS.write(length >> 16);
		bOS.write(length >> 8);
		bOS.write(length);
		bOS.write(serial >> 24);
		bOS.write(serial >> 16);
		bOS.write(serial >> 8);
		bOS.write(serial);
		bOS.write(event >> 24);
		bOS.write(event >> 16);
		bOS.write(event >> 8);
		bOS.write(event);
		bOS.write(userId >> 24);
		bOS.write(userId >> 16);
		bOS.write(userId >> 8);
		bOS.write(userId);
		bOS.write(reserved >> 24);
		bOS.write(reserved >> 16);
		bOS.write(reserved >> 8);
		bOS.write(reserved);
		return bOS.toByteArray();
	}



	public void fromBytes(byte[] receiveBytes){
		// TODO Auto-generated method stub
		flag = receiveBytes[0];
		encrypted = receiveBytes[1];
		version = (short) ((receiveBytes[2]) & 0xFF << 8);
		version += (short)((receiveBytes[3]) & 0xFF);
		length = (receiveBytes[4] & 0xFF) << 24;
		length += (receiveBytes[5] & 0xFF) << 16;
		length += (receiveBytes[6] & 0xFF) << 8;
		length += receiveBytes[7] & 0xFF;
		serial = (receiveBytes[8] & 0xFF) << 24;
		serial += (receiveBytes[9] & 0xFF) << 16;
		serial += (receiveBytes[10] & 0xFF) << 8;
		serial += receiveBytes[11] & 0xFF;
		event = (receiveBytes[12] & 0xFF) << 24;
		event += (receiveBytes[13] & 0xFF) << 16;
		event += (receiveBytes[14] & 0xFF) << 8;
		event += receiveBytes[15] & 0xFF;
		userId = (receiveBytes[16] & 0xFF) << 24;
		userId += (receiveBytes[17] & 0xFF) << 16;
		userId += (receiveBytes[18] & 0xFF) << 8;
		userId += receiveBytes[19] & 0xFF;
		reserved = (receiveBytes[20] & 0xFF) << 24;
		reserved += (receiveBytes[21] & 0xFF) << 16;
		reserved += (receiveBytes[22] & 0xFF) << 8;
		reserved += receiveBytes[23] & 0xFF;

	}


	/**
	 * 校验信息
	 * @return
	 */

	public boolean validate() {

		if ((this.getFlag() & 0xFF) == 0xEF && this.getVersion()==1 && this.getLength() >= 24) {
			System.out.println("getFlag: "+this.getFlag() + ",getVersion: "+this.getVersion() + ",getLength: "+ this.getLength());
			return true;
		}else{
			System.out.println("校验失败");
			System.out.println("getFlag: "+this.getFlag() + ",getVersion: "+this.getVersion() + ",getLength: "+ this.getLength());
			return false;
		}


	}

}
