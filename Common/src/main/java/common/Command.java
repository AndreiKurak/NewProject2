package common;

public interface Command {

    public ViewModel execute(Object options, Object globalOptions);

    /*
    //prepared statement для sql запросов
    //save load общая имплементация
    //подумать ещё над командами: класс для globaloptions и commandoptions (вместо создания полей)
    //придумать что-то вместо создания ифоф
    //не юзать рефлексию в командах +
     */
}