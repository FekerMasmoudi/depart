import dayjs from 'dayjs/esm';

import { ITrafic, NewTrafic } from './trafic.model';

export const sampleWithRequiredData: ITrafic = {
  id: '6b99feb5-c826-4994-b30f-42bc49d6b86c',
  deccent: 15462,
  decagenc: 52002,
  dedated: dayjs('2023-08-11'),
};

export const sampleWithPartialData: ITrafic = {
  id: '5ec9abe3-ff92-45cc-a270-15cf583e4dc2',
  deccent: 80857,
  decagenc: 90567,
  dedated: dayjs('2023-08-10'),
  clotrafic: 'Dale visionary orchestrate',
};

export const sampleWithFullData: ITrafic = {
  id: '556e4ea8-1d9a-4520-9ea1-fd33b2e646b9',
  deccent: 74971,
  decagenc: 58220,
  dedated: dayjs('2023-08-10'),
  ancien: 66633,
  vldtrafic: 'integrated',
  clotrafic: 'hybrid Gloves 1080p',
};

export const sampleWithNewData: NewTrafic = {
  deccent: 83214,
  decagenc: 59202,
  dedated: dayjs('2023-08-10'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
