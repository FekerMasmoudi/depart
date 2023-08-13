import dayjs from 'dayjs/esm';

import { ICentVehic, NewCentVehic } from './cent-vehic.model';

export const sampleWithRequiredData: ICentVehic = {
  id: 'b349e000-5054-4297-b289-fb394fb41c8f',
};

export const sampleWithPartialData: ICentVehic = {
  id: '7c3d6214-f126-411f-b6ac-821da569a529',
  cdmac: 'Mills Lead',
  dateff: dayjs('2023-08-10'),
  decagenc: 96413,
};

export const sampleWithFullData: ICentVehic = {
  id: '3bafc77d-8c59-44c2-82aa-e7bbffa66f9d',
  cdmac: 'withdrawal Generic',
  dateff: dayjs('2023-08-10'),
  deccent: 1185,
  decagenc: 4897,
};

export const sampleWithNewData: NewCentVehic = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
