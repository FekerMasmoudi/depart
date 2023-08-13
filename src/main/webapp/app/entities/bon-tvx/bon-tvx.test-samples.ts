import dayjs from 'dayjs/esm';

import { IBonTvx, NewBonTvx } from './bon-tvx.model';

export const sampleWithRequiredData: IBonTvx = {
  id: '2259c0cf-f6cb-4a29-8d0a-88159aba8bfd',
};

export const sampleWithPartialData: IBonTvx = {
  id: '7553baa5-ff19-4e73-9fde-80fecafa75fc',
  cdtier: 'CSS',
  cdmac: 'integrated up Bedfordshire',
  cdserv: 'content-based calculating Balboa',
  decagen: 81397,
  refbt: 'Personal',
  datbt: dayjs('2023-08-11'),
  heurfi: dayjs('2023-08-10'),
  observ: 'synergize Card out-of-the-box',
  datsrt: dayjs('2023-08-10'),
  indexdep: 22580,
  etab: 'Chair',
  compteur: 98218,
  cptorg: 18092,
  cdtyptr: 'needs-based',
  decstat: 'Sausages Regional',
  motifdep: 'Computers',
  datsorprev: dayjs('2023-08-10'),
  datmnqdu: dayjs('2023-08-11'),
  codstat: 'viral Lead',
  datvld: dayjs('2023-08-11'),
  datsais: dayjs('2023-08-10'),
};

export const sampleWithFullData: IBonTvx = {
  id: '0b9424c8-e585-4de5-8381-692a0e890a8d',
  cdexerc: 49134,
  numbt: 87670,
  cdtier: 'Paradigm withdrawal',
  cdmac: 'Greens South',
  maccdmac: 'Towels Legacy Analyst',
  cdserv: 'Ergonomic',
  decagen: 56217,
  dradecagen: 90379,
  cdorga: 'context-sensitive',
  refbt: 'Lebanese',
  datbt: dayjs('2023-08-10'),
  datdt: dayjs('2023-08-10'),
  datft: dayjs('2023-08-10'),
  vld: 'virtual Steel',
  typtvx: 'Usability',
  heurdb: dayjs('2023-08-10'),
  heurfi: dayjs('2023-08-10'),
  observ: 'Officer',
  datsrt: dayjs('2023-08-10'),
  heursr: dayjs('2023-08-11'),
  obstest: 'bandwidth',
  indexdep: 58804,
  indexarr: 91455,
  immatex: 'override 24/365 Delaware',
  nomchauff: 'Grocery',
  numpermis: 'Self-enabling',
  etab: 'Infrastructure e-services Practical',
  compteur: 72738,
  cptorg: 53579,
  cdtyptr: 'Chair',
  decstat: 'Hawaii',
  testeur: 70657,
  motifdep: 'rich calculating maximized',
  cdtypmnt: 'mobile',
  datsorprev: dayjs('2023-08-11'),
  datmnqdu: dayjs('2023-08-11'),
  datmnqau: dayjs('2023-08-10'),
  datentant: dayjs('2023-08-11'),
  codstat: 'Account',
  datvld: dayjs('2023-08-10'),
  observ1: 'Specialist invoice',
  testeur1: 62740,
  validag: 54215,
  datsais: dayjs('2023-08-10'),
};

export const sampleWithNewData: NewBonTvx = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
