package com.solvd.metro.impl;

import com.solvd.metro.Metro;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;

public interface IPars {

    //методамы джавы проверить что xml файл подходит xsd схему
    Metro parse(File file);
}
