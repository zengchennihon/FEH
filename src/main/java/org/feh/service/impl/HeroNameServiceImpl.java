package org.feh.service.impl;

import org.feh.service.HeroNameService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class HeroNameServiceImpl implements HeroNameService {

}
