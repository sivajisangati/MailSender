package com.yatra.mailsender.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<String> fetchData() {
		String sql = "SELECT super_pnr, booking_xml\r\n" + 
				"FROM mo_transfer_queue\r\n" + 
				"WHERE mo_transfer NOT IN ('KAFKA_MO_DROP_SUCCESS', 'TRANSFERRED', 'KAFKA_ACK_DUPLICATE')\r\n" + 
				"AND DATE(created_on) = DATE_SUB(CURDATE(), INTERVAL 1 DAY);\r\n" + 
				"";
		return jdbcTemplate.queryForList(sql, String.class);
	}
}
