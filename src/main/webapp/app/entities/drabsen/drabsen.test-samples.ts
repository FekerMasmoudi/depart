import dayjs from 'dayjs/esm';

import { IDrabsen, NewDrabsen } from './drabsen.model';

export const sampleWithRequiredData: IDrabsen = {
  id: '63b9904f-0452-4366-9513-60cbc211edf0',
};

export const sampleWithPartialData: IDrabsen = {
  id: 'd763f222-285c-4d72-aad4-ce67e29eebe5',
  cdtypab: 'payment Architect Frozen',
  nbrabs: 25547,
  observaabs: 'pixel Unbranded Research',
};

export const sampleWithFullData: IDrabsen = {
  id: '97c0415d-4dbd-45bb-b3a7-2f6bd9351bc9',
  cdtypab: 'synthesizing Yemeni',
  matric: 60726,
  databs: dayjs('2023-08-10'),
  numabs: 46971,
  nbrabs: 12275,
  validabs: 27223,
  observaabs: 'black Intelligent value-added',
  cd1: 407,
  cd2: 51306,
  cd3: 16286,
};

export const sampleWithNewData: NewDrabsen = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
