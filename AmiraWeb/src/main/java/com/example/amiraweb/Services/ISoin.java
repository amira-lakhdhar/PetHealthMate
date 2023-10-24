package com.example.amiraweb.Services;

import com.example.amiraweb.Entity.Soin;

import java.util.List;

public interface ISoin  {


        public List<Soin> getAllSoins();

        public Soin getSoinById(Long id);

        public Soin createSoin(Soin soin);

        public Soin updateSoin(Long id, Soin soin) ;

        public void deleteSoin(Long id);



}
