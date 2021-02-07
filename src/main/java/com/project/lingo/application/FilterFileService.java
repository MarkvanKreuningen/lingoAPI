package com.project.lingo.application;

import com.project.lingo.port.dao.FilterFile;
import com.project.lingo.port.dao.FilterFileDao;

import java.util.ArrayList;

public class FilterFileService {
    FilterFile filterFile = new FilterFileDao();

    public ArrayList<String> getFilteredList(){
        return filterFile.getList();
    }
}
