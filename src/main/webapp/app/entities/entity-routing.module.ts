import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'route',
        data: { pageTitle: 'departdbApp.route.home.title' },
        loadChildren: () => import('./route/route.module').then(m => m.RouteModule),
      },
      {
        path: 'depart',
        data: { pageTitle: 'departdbApp.depart.home.title' },
        loadChildren: () => import('./depart/depart.module').then(m => m.DepartModule),
      },
      {
        path: 'deprotat',
        data: { pageTitle: 'departdbApp.deprotat.home.title' },
        loadChildren: () => import('./deprotat/deprotat.module').then(m => m.DeprotatModule),
      },
      {
        path: 'station',
        data: { pageTitle: 'departdbApp.station.home.title' },
        loadChildren: () => import('./station/station.module').then(m => m.StationModule),
      },
      {
        path: 'itineraire',
        data: { pageTitle: 'departdbApp.itineraire.home.title' },
        loadChildren: () => import('./itineraire/itineraire.module').then(m => m.ItineraireModule),
      },
      {
        path: 'ligne',
        data: { pageTitle: 'departdbApp.ligne.home.title' },
        loadChildren: () => import('./ligne/ligne.module').then(m => m.LigneModule),
      },
      {
        path: 'agence',
        data: { pageTitle: 'departdbApp.agence.home.title' },
        loadChildren: () => import('./agence/agence.module').then(m => m.AgenceModule),
      },
      {
        path: 'center',
        data: { pageTitle: 'departdbApp.center.home.title' },
        loadChildren: () => import('./center/center.module').then(m => m.CenterModule),
      },
      {
        path: 'affectagent',
        data: { pageTitle: 'departdbApp.affectagent.home.title' },
        loadChildren: () => import('./affectagent/affectagent.module').then(m => m.AffectagentModule),
      },
      {
        path: 'bordereau',
        data: { pageTitle: 'departdbApp.bordereau.home.title' },
        loadChildren: () => import('./bordereau/bordereau.module').then(m => m.BordereauModule),
      },
      {
        path: 'fonc-agent',
        data: { pageTitle: 'departdbApp.foncAgent.home.title' },
        loadChildren: () => import('./fonc-agent/fonc-agent.module').then(m => m.FoncAgentModule),
      },
      {
        path: 'machine',
        data: { pageTitle: 'departdbApp.machine.home.title' },
        loadChildren: () => import('./machine/machine.module').then(m => m.MachineModule),
      },
      {
        path: 'bon-tvx',
        data: { pageTitle: 'departdbApp.bonTvx.home.title' },
        loadChildren: () => import('./bon-tvx/bon-tvx.module').then(m => m.BonTvxModule),
      },
      {
        path: 'cent-vehic',
        data: { pageTitle: 'departdbApp.centVehic.home.title' },
        loadChildren: () => import('./cent-vehic/cent-vehic.module').then(m => m.CentVehicModule),
      },
      {
        path: 'typ-stat',
        data: { pageTitle: 'departdbApp.typStat.home.title' },
        loadChildren: () => import('./typ-stat/typ-stat.module').then(m => m.TypStatModule),
      },
      {
        path: 'groupe',
        data: { pageTitle: 'departdbApp.groupe.home.title' },
        loadChildren: () => import('./groupe/groupe.module').then(m => m.GroupeModule),
      },
      {
        path: 'periode',
        data: { pageTitle: 'departdbApp.periode.home.title' },
        loadChildren: () => import('./periode/periode.module').then(m => m.PeriodeModule),
      },
      {
        path: 'affec-agent',
        data: { pageTitle: 'departdbApp.affecAgent.home.title' },
        loadChildren: () => import('./affec-agent/affec-agent.module').then(m => m.AffecAgentModule),
      },
      {
        path: 'service-rot',
        data: { pageTitle: 'departdbApp.serviceRot.home.title' },
        loadChildren: () => import('./service-rot/service-rot.module').then(m => m.ServiceRotModule),
      },
      {
        path: 'rh-agent',
        data: { pageTitle: 'departdbApp.rhAgent.home.title' },
        loadChildren: () => import('./rh-agent/rh-agent.module').then(m => m.RhAgentModule),
      },
      {
        path: 'motifa',
        data: { pageTitle: 'departdbApp.motifa.home.title' },
        loadChildren: () => import('./motifa/motifa.module').then(m => m.MotifaModule),
      },
      {
        path: 'motifchserv',
        data: { pageTitle: 'departdbApp.motifchserv.home.title' },
        loadChildren: () => import('./motifchserv/motifchserv.module').then(m => m.MotifchservModule),
      },
      {
        path: 'trafic',
        data: { pageTitle: 'departdbApp.trafic.home.title' },
        loadChildren: () => import('./trafic/trafic.module').then(m => m.TraficModule),
      },
      {
        path: 'rot-rserv',
        data: { pageTitle: 'departdbApp.rotRserv.home.title' },
        loadChildren: () => import('./rot-rserv/rot-rserv.module').then(m => m.RotRservModule),
      },
      {
        path: 'modif',
        data: { pageTitle: 'departdbApp.modif.home.title' },
        loadChildren: () => import('./modif/modif.module').then(m => m.ModifModule),
      },
      {
        path: 'drtypab',
        data: { pageTitle: 'departdbApp.drtypab.home.title' },
        loadChildren: () => import('./drtypab/drtypab.module').then(m => m.DrtypabModule),
      },
      {
        path: 'drabsen',
        data: { pageTitle: 'departdbApp.drabsen.home.title' },
        loadChildren: () => import('./drabsen/drabsen.module').then(m => m.DrabsenModule),
      },
      {
        path: 'external-api',
        data: { pageTitle: 'departdbApp.externalApi.home.title' },
        loadChildren: () => import('./external-api/external-api.module').then(m => m.ExternalApiModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
