import { IAgence, NewAgence } from './agence.model';

export const sampleWithRequiredData: IAgence = {
  id: '0a132ff0-75f7-4ef8-882d-479c6ffa2767',
  deccent: 44252,
  decagenc: 11534,
};

export const sampleWithPartialData: IAgence = {
  id: '10136dd1-ac4c-46c3-899e-1ffac7fc0cc0',
  deccent: 81783,
  decagenc: 20450,
  defaultagenc: 'Usability',
};

export const sampleWithFullData: IAgence = {
  id: 'e002e9c9-688e-476e-b3d8-e98b5cc66ef9',
  deccent: 93289,
  decagenc: 32003,
  delagenc: 'Forest',
  defaultagenc: 'multi-state',
};

export const sampleWithNewData: NewAgence = {
  deccent: 80778,
  decagenc: 49754,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
