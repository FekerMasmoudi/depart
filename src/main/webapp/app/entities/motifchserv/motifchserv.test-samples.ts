import { IMotifchserv, NewMotifchserv } from './motifchserv.model';

export const sampleWithRequiredData: IMotifchserv = {
  id: 'a8b66897-8586-42ea-a63b-af16a57729a4',
  decmotif: 95197,
};

export const sampleWithPartialData: IMotifchserv = {
  id: '1260c8f2-4aa8-482c-8dfd-b08b6cf622cf',
  decmotif: 42917,
  x: 'payment Berkshire payment',
};

export const sampleWithFullData: IMotifchserv = {
  id: '1ee0f441-71e5-4ab6-a2e7-1468d73ef119',
  decmotif: 714,
  delmotif: 'hard blue',
  x: 'navigate Assistant',
  vs: 'Handmade archive Brazil',
};

export const sampleWithNewData: NewMotifchserv = {
  decmotif: 66178,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
