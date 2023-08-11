import dayjs from 'dayjs/esm';

import { IFoncAgent, NewFoncAgent } from './fonc-agent.model';

export const sampleWithRequiredData: IFoncAgent = {
  id: '0264872a-41d5-4c65-a555-1893326d635d',
};

export const sampleWithPartialData: IFoncAgent = {
  id: 'a8e38f91-f87f-42c1-a415-894ed552c7f4',
  dateff: dayjs('2023-08-11'),
  valide: 'SCSI optical',
};

export const sampleWithFullData: IFoncAgent = {
  id: '7e2b624b-97f5-4d2d-9b86-52cfad4ff80a',
  cdfonc: 'virtual haptic wireless',
  matric: 57053,
  nom: 'Vermont',
  prenom: 'Tasty',
  dateff: dayjs('2023-08-10'),
  valide: 'system transition panel',
};

export const sampleWithNewData: NewFoncAgent = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
