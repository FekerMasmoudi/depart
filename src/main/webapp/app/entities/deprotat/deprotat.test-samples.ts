import dayjs from 'dayjs/esm';

import { IDeprotat, NewDeprotat } from './deprotat.model';

export const sampleWithRequiredData: IDeprotat = {
  id: '5baca89e-dcac-4627-aa93-a19027ee567d',
  deccent: 26934,
  decagenc: 49617,
  dedated: dayjs('2023-08-04'),
  denumdp: 28535,
  decserv: 17509,
  decoper: 'Center algorithm',
  decsean: 'parse',
};

export const sampleWithPartialData: IDeprotat = {
  id: '1191112a-a127-4e4b-a0ba-6154edc4ca63',
  deccent: 31268,
  decagenc: 11917,
  dedated: dayjs('2023-08-03'),
  denumdp: 68921,
  decserv: 14054,
  decoper: 'compress Account Granite',
  decsean: 'Plastic',
  numrotat: 81087,
  ligdeccent: 56985,
  ligdecagenc: 10780,
  denumli: 'program',
  decstat: 'invoice Berkshire',
  matric1: 55814,
  cdmac: 'Wooden Money',
  hdeparte: dayjs('2023-08-04'),
  rannul: 'Health',
  motifa: 25555,
  paye: 27576,
  cd2: 36972,
  idapex: 17549,
};

export const sampleWithFullData: IDeprotat = {
  id: 'd1faddd5-4f87-4321-a608-594359e52968',
  deccent: 13493,
  decagenc: 10342,
  dedated: dayjs('2023-08-03'),
  denumdp: 17201,
  decserv: 95309,
  decoper: 'Licensed B2C',
  decsean: '24/7 digital Michigan',
  numrotat: 43484,
  ligdeccent: 63426,
  ligdecagenc: 40900,
  denumli: 'payment',
  decstat: 'artificial',
  decsta1: 'Fully-configurable',
  matric: 5379,
  matric1: 99965,
  cdmac: 'Tennessee Automotive Multi-layered',
  hdeparte: dayjs('2023-08-04'),
  hretoure: dayjs('2023-08-03'),
  harralle: dayjs('2023-08-04'),
  harrrete: dayjs('2023-08-03'),
  rannul: 'next-generation monitor',
  km: 47757,
  motifa: 87894,
  observ: 'index Future-proofed orchestrate',
  recettesvoy: 16603,
  nbrevoy: 99061,
  paye: 16217,
  cd1: 87349,
  cd2: 88060,
  cd3: 10025,
  decmotifcha: 90091,
  decmotifrea: 77704,
  idapex: 89498,
  plusmoins: 'encompassing',
  a: 'Kids deposit Steel',
  r: 'Towels',
};

export const sampleWithNewData: NewDeprotat = {
  deccent: 31966,
  decagenc: 75743,
  dedated: dayjs('2023-08-03'),
  denumdp: 90966,
  decserv: 89740,
  decoper: 'Wooden enable contingency',
  decsean: 'of Orchestrator',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
