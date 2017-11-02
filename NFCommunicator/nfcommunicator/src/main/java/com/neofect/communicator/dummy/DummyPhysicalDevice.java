package com.neofect.communicator.dummy;

import android.util.Log;

import static com.neofect.communicator.util.ByteArrayConverter.byteArrayToHex;

/**
 * @author neo.kim@neofect.com
 * @date Nov 02, 2017
 */
public abstract class DummyPhysicalDevice {

	private static final String LOG_TAG = "DummyPhysicalDevice";

	protected DummyConnection connection;

	public String getDeviceName() {
		return "DummyPhysicalDevice";
	}

	public String getDeviceIdentifier() {
		return "DummyPhysicalDeviceIdentifier";
	}

	protected void connect(final DummyConnection connection) {
		Log.d(LOG_TAG, "connect: ");
	}

	protected void notifyConnected() {
		Log.d(LOG_TAG, "notifyConnected: ");
		connection.onConnected();
	}

	protected void disconnect() {
		Log.d(LOG_TAG, "disconnect: ");
	}

	protected void receive(byte[] data) {
		Log.d(LOG_TAG, "receive: [" + byteArrayToHex(data) + "]");
	}

	protected void notifyRead(byte[] data) {
		Log.d(LOG_TAG, "notifyRead: [" + byteArrayToHex(data) + "]");
		connection.onRead(data);
	}
}
