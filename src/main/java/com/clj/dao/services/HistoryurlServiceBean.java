package com.clj.dao.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.clj.dao.interfaces.HistoryurlServiceInter;

@Service
@Transactional(propagation=Propagation.REQUIRED , readOnly=true)
public class HistoryurlServiceBean extends BaseServiceBean implements HistoryurlServiceInter{

	
}
