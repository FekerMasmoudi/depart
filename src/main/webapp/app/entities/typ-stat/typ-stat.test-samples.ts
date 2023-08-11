import { ITypStat, NewTypStat } from './typ-stat.model';

export const sampleWithRequiredData: ITypStat = {
  id: '6f9fde17-3c1c-4d9b-9ded-486e91a70d7a',
  dectyst: 'Rupee',
};

export const sampleWithPartialData: ITypStat = {
  id: 'f81a59bb-402d-49c4-af1e-9528633ab6a6',
  dectyst: 'Towels revolutionary',
  deltyst: 'synthesizing Chicken',
};

export const sampleWithFullData: ITypStat = {
  id: '40acbfc2-1fa6-4caf-a324-b71c49bb7e4d',
  dectyst: 'Jordan hacking sensor',
  deltyst: 'Montserrat parse Tobago',
};

export const sampleWithNewData: NewTypStat = {
  dectyst: 'Loan hard Barbados',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
