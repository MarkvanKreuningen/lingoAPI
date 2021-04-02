package com.project.lingo.application;

import com.project.lingo.data.dao.FilterFile;
import com.project.lingo.data.dao.FilterFileDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FilterFileService implements IFilterFileService{

    @Override
    public ArrayList<String> getFilteredList(){
        FilterFile filterFile = new FilterFileDao();
        return filterFile.getList();
    }
}
