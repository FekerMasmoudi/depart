import dayjs from 'dayjs/esm';

import { ILigne, NewLigne } from './ligne.model';

export const sampleWithRequiredData: ILigne = {
  id: 'e3efcb36-2ded-4e13-847a-9b5b71a27059',
};

export const sampleWithPartialData: ILigne = {
  id: '5bc10347-f02e-4839-acf1-3553212868ba',
  denumli: 'invoice Chief Aruba',
  dectyta: 'seamless',
  denomli: 'Shoes',
  dectyeq: 'compelling',
  dedural: 90418,
  detrjvr: 56487,
  statlig: '3rd',
  lig1: 21487,
  valide: 'Multi-layered',
};

export const sampleWithFullData: ILigne = {
  id: '91a48657-6504-4354-a47f-01026abcd74e',
  deccent: 69227,
  decagenc: 11629,
  denumli: 'generation Web',
  dectyli: 'Parks',
  dectyta: 'quantifying THX HDD',
  denomli: 'Legacy deposit CSS',
  dectyeq: 'Shirt payment',
  denbrkm: 75974,
  detparc: 86054,
  dedural: 21151,
  dedurrt: 85209,
  detrjva: 72307,
  detrjvr: 70889,
  depiste: 42576,
  statlig: 'Customer',
  lig: 'Implemented payment',
  lig1: 55711,
  valide: 'engage Metal Analyst',
  denumli2: 'Customer',
  kml: '../fake-data/blob/hipster.png',
  kmlContentType: 'unknown',
  description: 'Awesome AI Internal',
  mimtype: 'SCSI homogeneous Eritrea',
  filename: 'Sterling',
  charset: 'navigate',
  lastupdate: dayjs('2023-08-04T08:32'),
};

export const sampleWithNewData: NewLigne = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
