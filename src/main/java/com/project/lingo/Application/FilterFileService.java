package com.project.lingo.Application;

import com.project.lingo.Data.dao.FilterFile;
import com.project.lingo.Data.dao.FilterFileDao;

import java.util.ArrayList;

public class FilterFileService {
    FilterFile filterFile = new FilterFileDao();

    public ArrayList<String> getFilteredList(){
        return filterFile.getList();
    }
}
