import { IServiceRot, NewServiceRot } from './service-rot.model';

export const sampleWithRequiredData: IServiceRot = {
  id: '9ef15a42-870e-4dbc-b613-1920ba338d2d',
  deccent: 1480,
  decagenc: 19789,
  decserv: 41785,
  codgrp: 21393,
};

export const sampleWithPartialData: IServiceRot = {
  id: '399182bf-d49e-4d7b-9fbf-b5af3bb88280',
  deccent: 32414,
  decagenc: 26343,
  decserv: 68585,
  codgrp: 59132,
};

export const sampleWithFullData: IServiceRot = {
  id: 'e2ab84d9-ac6a-40a4-ade0-80d9162ef6c6',
  deccent: 64396,
  decagenc: 98066,
  decserv: 13466,
  codgrp: 49604,
  delserv: 'Soft hub International',
  ordserv: 20581,
};

export const sampleWithNewData: NewServiceRot = {
  deccent: 79841,
  decagenc: 60961,
  decserv: 64088,
  codgrp: 92307,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
