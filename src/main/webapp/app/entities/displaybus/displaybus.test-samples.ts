import { IDisplaybus, NewDisplaybus } from './displaybus.model';

export const sampleWithRequiredData: IDisplaybus = {
  id: '398d7cec-4497-437d-bc70-3b4144c0bc9e',
};

export const sampleWithPartialData: IDisplaybus = {
  id: '6b5ade0b-0b57-4922-906a-1e87da825d25',
  lang: 'transition',
  vehicule: 'generating Sleek Steel',
  detail_ligne: 'Sweden transmitting Automotive',
  deltyli: 'generate Investment',
};

export const sampleWithFullData: IDisplaybus = {
  id: 'e70005d7-b6dd-4599-a1c0-b83ea81fe090',
  lang: 'silver UIC-Franc',
  vehicule: 'auxiliary',
  num_appel: 90262,
  detail_ligne: 'Synchronised',
  ligne: 'global',
  direction: 'Account',
  denumli: 'Account driver navigate',
  deltyli: 'deploy e-markets 1080p',
};

export const sampleWithNewData: NewDisplaybus = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
