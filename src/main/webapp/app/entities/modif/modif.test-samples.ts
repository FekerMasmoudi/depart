import dayjs from 'dayjs/esm';

import { IModif, NewModif } from './modif.model';

export const sampleWithRequiredData: IModif = {
  id: '90e948e4-0ac0-4f95-8f5b-1dcf4aaf0f02',
};

export const sampleWithPartialData: IModif = {
  id: '4367c066-f9b8-416f-a175-391d1134a4a2',
  deccent: 21073,
  decagenc: 32242,
  dedated: dayjs('2023-08-10'),
  denumdp: 54891,
  decserv: 19847,
  decsean: 'Micronesia Chair',
  matric: 99548,
  cd1: 23188,
  heur: dayjs('2023-08-10'),
  typ: 'Rustic Robust approach',
};

export const sampleWithFullData: IModif = {
  id: 'a940ddd4-948d-4758-8687-a7336a8644a2',
  deccent: 34869,
  decagenc: 63960,
  dedated: dayjs('2023-08-11'),
  denumdp: 70574,
  decserv: 10346,
  decoper: 'architecture Islands lime',
  decsean: 'Parkways',
  numrotat: 58807,
  matric: 63938,
  cd1: 63108,
  decmotif: 30584,
  heur: dayjs('2023-08-11'),
  chre: 'Bike',
  typ: 'compress plum International',
};

export const sampleWithNewData: NewModif = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
