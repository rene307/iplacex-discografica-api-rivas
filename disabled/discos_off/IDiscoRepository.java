package com.iplacex.discografia.discos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IDiscoRepository extends MongoRepository<Disco, String> { }
