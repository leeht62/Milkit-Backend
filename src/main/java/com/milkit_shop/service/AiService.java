package com.milkit_shop.service;

import java.util.List;
import java.util.Map;

public record AiService(String sqlQuery, List<Map<String, Object>> results) { }