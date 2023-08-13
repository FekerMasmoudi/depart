import { IRoute, NewRoute } from './route.model';

export const sampleWithRequiredData: IRoute = {
  id: '69f25ff7-040b-4f4b-83ae-3058586f07f4',
  agency_id: 'flexibility',
  route_short_name: 'overriding Orchestrator Alabama',
  route_long_name: 'Guarani',
  route_desc: 'Cuba',
  route_type: 'invoice functionalities',
};

export const sampleWithPartialData: IRoute = {
  id: '7bdf72fc-fd2b-4d44-8e68-63a4a7aef37e',
  agency_id: 'markets transmitter',
  route_short_name: 'Kyrgyz',
  route_long_name: 'Car Handcrafted synergies',
  route_desc: 'Business-focused Carolina',
  route_type: 'Oklahoma e-services Officer',
};

export const sampleWithFullData: IRoute = {
  id: '65ef38b3-7ce0-439f-a327-a812e58b676c',
  agency_id: 'niches synergistic',
  route_short_name: 'up',
  route_long_name: 'mobile redundant back-end',
  route_desc: 'Manager Fantastic',
  route_type: 'Grove hard',
};

export const sampleWithNewData: NewRoute = {
  agency_id: 'salmon User-centric',
  route_short_name: 'Metrics',
  route_long_name: 'budgetary',
  route_desc: 'Legacy Lead',
  route_type: 'calculate',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
