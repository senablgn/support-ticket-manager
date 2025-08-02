package com.senablgn.supportsystem.support_ticket_manager.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionInfo <E>{
	private String path;
	private String host;
	private Date date;
	private E message;
}
