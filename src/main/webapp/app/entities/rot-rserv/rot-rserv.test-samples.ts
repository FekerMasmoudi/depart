import dayjs from 'dayjs/esm';

import { IRotRserv, NewRotRserv } from './rot-rserv.model';

export const sampleWithRequiredData: IRotRserv = {
  deccent: 63075,
  decagenc: 54036,
  dedated: dayjs('2023-08-10'),
  matric: 37198,
  heurdeb: dayjs('2023-08-10T11:54'),
  heurfin: dayjs('2023-08-11T03:06'),
  id: '958a6353-a0dc-4c4d-880a-fae704208500',
};

export const sampleWithPartialData: IRotRserv = {
  deccent: 63246,
  decagenc: 4506,
  dedated: dayjs('2023-08-10'),
  matric: 30227,
  heurdeb: dayjs('2023-08-11T03:49'),
  heurfin: dayjs('2023-08-10T21:46'),
  lieedeb: dayjs('2023-08-10T09:53'),
  program: 'Home North',
  cd1: 59540,
  cd2: 38636,
  cd3: 55674,
  id: '98f660b9-10a0-4d26-bc2c-d20697e24166',
  annul: 'Granite Louisiana',
};

export const sampleWithFullData: IRotRserv = {
  deccent: 83442,
  decagenc: 54390,
  dedated: dayjs('2023-08-10'),
  matric: 50974,
  heurdeb: dayjs('2023-08-11T05:17'),
  heurfin: dayjs('2023-08-10T21:49'),
  statut: 'Frozen',
  lieedeb: dayjs('2023-08-11T08:08'),
  lieefin: dayjs('2023-08-11T05:17'),
  program: 'Avenue',
  cd1: 2223,
  cd2: 43402,
  cd3: 77543,
  id: '14f5ba92-7242-4f2c-af95-1b9ec0d148e0',
  annul: 'reboot neural',
};

export const sampleWithNewData: NewRotRserv = {
  deccent: 50566,
  decagenc: 24155,
  dedated: dayjs('2023-08-10'),
  matric: 58514,
  heurdeb: dayjs('2023-08-10T21:26'),
  heurfin: dayjs('2023-08-10T18:19'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
