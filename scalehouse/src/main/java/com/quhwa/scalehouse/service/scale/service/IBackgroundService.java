package com.quhwa.scalehouse.service.scale.service;

import java.util.List;

import com.quhwa.scalehouse.common.model.ResultEntity;
import com.quhwa.scalehouse.service.scale.model.Background;

public interface IBackgroundService extends IBaseService<Background>{

	ResultEntity<List<Background>> queryByUserAndPassword(Background back);
	
}
