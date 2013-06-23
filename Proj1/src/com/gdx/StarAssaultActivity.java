package com.gdx;

import android.os.Bundle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class StarAssaultActivity extends AndroidApplication {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useAccelerometer = false;
		config.useCompass = false;
		config.useWakelock = false;
		config.useGL20 = true;
		initialize(new StarAssault(), config);
	}
//	@Override
//	public void create() {
//		// TODO Auto-generated method stub
//		super.
//	}
//
//	@Override
//	public void dispose() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void pause() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void render() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void resize(int arg0, int arg1) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void resume() {
//		// TODO Auto-generated method stub
//		
//	}

}
