import dayjs from 'dayjs/esm';

import { IRhAgent, NewRhAgent } from './rh-agent.model';

export const sampleWithRequiredData: IRhAgent = {
  id: '229c0d63-d490-4fc1-9922-8395415466e7',
};

export const sampleWithPartialData: IRhAgent = {
  id: '67cf9d64-76f4-4275-8622-52a77818e164',
  decjour: 'installation',
  dateffrh: dayjs('2023-08-10'),
  decoper: 'Ville Developer Auto',
};

export const sampleWithFullData: IRhAgent = {
  id: 'c00fd9bf-cde2-4420-990a-237459de68e0',
  matric: 29179,
  decjour: 'archive Senior parse',
  dateffrh: dayjs('2023-08-10'),
  decoper: 'Human Configuration',
  deccent: 78510,
  decagenc: 3676,
};

export const sampleWithNewData: NewRhAgent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
