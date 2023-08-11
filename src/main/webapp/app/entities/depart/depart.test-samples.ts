import dayjs from 'dayjs/esm';

import { IDepart, NewDepart } from './depart.model';

export const sampleWithRequiredData: IDepart = {
  id: '516381bd-c1ad-4c10-9541-1beb89c99710',
  deccent: 7244,
  decagenc: 34374,
  decserv: 87377,
  decoper: 'input Global',
  decsean: 'Devolved',
  dedated: dayjs('2023-08-03'),
  denumdp: 46240,
};

export const sampleWithPartialData: IDepart = {
  id: '9b13cc59-30c1-49b9-a422-0b303bd916ef',
  deccent: 68975,
  decagenc: 41802,
  decserv: 79932,
  decoper: 'plug-and-play Manager relationships',
  decsean: 'Jewelery invoice',
  dedated: dayjs('2023-08-03'),
  denumdp: 74776,
  matric: 63781,
  deheups: dayjs('2023-08-03T23:02'),
  deheufs: dayjs('2023-08-03T13:57'),
  denbrro: 57978,
  deheuaa: dayjs('2023-08-03T10:58'),
  deheudr: dayjs('2023-08-03T16:46'),
  deheupd: dayjs('2023-08-03T23:48'),
  deampli: dayjs('2023-08-03T17:56'),
  deannul: 'Cheese',
  decclot: 'Directives Reduced',
  execute: 'Metrics',
  motifa: 'Berkshire National Graphical',
  observ: 'ADP',
  recettes: 20497,
  nbrevoy: 70658,
  decmotifch: 22096,
  cd1: 43840,
  cd2: 15013,
  decmotifrea: 38960,
};

export const sampleWithFullData: IDepart = {
  id: '7dd9119f-a5c7-4257-8ea5-1615cb9ff525',
  deccent: 89733,
  decagenc: 99470,
  decserv: 54889,
  decoper: 'Fantastic Persistent Fantastic',
  decsean: 'program',
  dedated: dayjs('2023-08-03'),
  denumdp: 59481,
  matric: 7811,
  matric1: 27937,
  cdmac: 19649,
  deheups: dayjs('2023-08-03T14:21'),
  deheufs: dayjs('2023-08-04T09:00'),
  denbrro: 99144,
  deheuaa: dayjs('2023-08-03T15:17'),
  deheudr: dayjs('2023-08-04T03:35'),
  deheupd: dayjs('2023-08-04T04:32'),
  deampli: dayjs('2023-08-04T02:09'),
  obsind: 'Borders azure Indiana',
  vldroul: 'infrastructures',
  deetat: 'Carolina Cotton',
  deannul: 'Executive Rwanda',
  decclot: 'engine Consultant',
  execute: 'Buckinghamshire',
  motifa: 'FTP Pre-emptive Shoes',
  observ: 'Philippines encoding Books',
  recettes: 55990,
  nbrevoy: 82122,
  decmotifch: 78267,
  decmotifre: 46005,
  cd1: 2856,
  cd2: 80216,
  cd3: 74006,
  decmotifcha: 43827,
  decmotifrea: 91438,
};

export const sampleWithNewData: NewDepart = {
  deccent: 98404,
  decagenc: 33494,
  decserv: 45088,
  decoper: 'Functionality',
  decsean: 'standardization Bedfordshire application',
  dedated: dayjs('2023-08-04'),
  denumdp: 31383,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
