package com.project.lingo.Application;


public class ServiceProvider {
    private static final FilterFileService filterFileService = new FilterFileService();

    public static FilterFileService getFilterFileService() {
        return filterFileService;
    }

    private static final LingoService lingoService = new LingoService();

    public static LingoService getLingoService(){
        return lingoService;
    }

    public static final SpelerService spelerService = new SpelerService();

    public static SpelerService getSpelerService() {
        return spelerService;
    }
}
