package com.project.lingo.application;

public class ServiceProvider {
    private static FilterFileService filterFileService = new FilterFileService();

    public static FilterFileService getFilterFileService() {
        return filterFileService;
    }

    private static LingoService lingoService = new LingoService();

    public static LingoService getLingoService(){
        return lingoService;
    }
}
