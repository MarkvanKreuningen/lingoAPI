package com.project.lingo.Application;

import com.project.lingo.Data.dao.FilterFile;
import com.project.lingo.Data.dao.FilterFileDao;
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
