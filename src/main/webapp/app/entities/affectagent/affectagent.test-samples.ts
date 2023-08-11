import { IAffectagent, NewAffectagent } from './affectagent.model';

export const sampleWithRequiredData: IAffectagent = {
  id: '236afda9-20a4-4094-8495-bfefc8ee45cd',
  deccent: 3260,
  decagenc: 84041,
  decserv: 69609,
  decoper: 'capability Baht Montana',
  decsean: 'application',
  cdmois: 26800,
  cdsocie: 'XSS maroon calculate',
  decexer: 67891,
  matric: 33234,
  matric2: 14582,
  cdmac: 'Books',
};

export const sampleWithPartialData: IAffectagent = {
  id: 'ab6df0e3-1504-4b55-a87f-673b1e107db5',
  deccent: 15639,
  decagenc: 51165,
  decserv: 77044,
  decoper: 'Forward',
  decsean: 'Intelligent Accountability invoice',
  cdmois: 85793,
  cdsocie: 'website Usability',
  decexer: 95630,
  matric: 58130,
  matric2: 46495,
  cdmac: 'withdrawal',
};

export const sampleWithFullData: IAffectagent = {
  id: 'a5ecfbf9-3c62-408c-8a65-08e004c40bc1',
  deccent: 6812,
  decagenc: 95185,
  decserv: 51197,
  decoper: 'Iowa',
  decsean: 'virtual back-end',
  cdmois: 11981,
  cdsocie: 'revolutionary bandwidth-monitored',
  decexer: 21915,
  matric: 24309,
  matric2: 1740,
  cdmac: 'Niger COM Grove',
};

export const sampleWithNewData: NewAffectagent = {
  deccent: 3612,
  decagenc: 92557,
  decserv: 35173,
  decoper: 'Jewelery',
  decsean: 'Function-based Diverse',
  cdmois: 22301,
  cdsocie: 'azure',
  decexer: 8842,
  matric: 38449,
  matric2: 22259,
  cdmac: 'e-services Tactics Bedfordshire',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
