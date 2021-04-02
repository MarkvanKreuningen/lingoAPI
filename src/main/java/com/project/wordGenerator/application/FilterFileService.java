package com.project.wordGenerator.application;

import com.project.wordGenerator.data.dao.FilterFile;
import com.project.wordGenerator.data.dao.FilterFileDao;
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
